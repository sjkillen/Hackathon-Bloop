const dotenv = require("dotenv")
const path = require("path")
const express = require("express")
const app = express()
const http = require('http').Server(app)
const io = require("socket.io")(http)
const ntpClient = require('ntp-client')
const fs = require("fs")

const sounds = fs.readdirSync(path.join(__dirname, "sounds"));

dotenv.config()

app.use("/sound", express.static(path.resolve(__dirname, "sounds")))
app.get("/sounds", (req, res) => {
    res.json(sounds);
});
app.use("/", express.static(path.resolve(__dirname, "orchestrator/dist")))

http.listen(process.env.PORT, function (){
  console.log('listening on *:'+process.env.PORT);
});

const ONE_SEC = 1000 * 1000;

io.on("connection", socket => {
    socket.emit("connection", { sounds });
    socket.on("ping", d => {
        console.log(d)
        socket.emit("pong");
    });
    socket.on("showtime", show => {
        ntpClient.getNetworkTime("time.nist.gov", 123, function(err, date) {
        const startTime = date.getTime() + ONE_SEC * 10;
            show.forEach(moment => {
                moment.time = startTime + moment.time * 1000
            });
            socket.broadcast.emit("showtime", show);
        });
    })
})


