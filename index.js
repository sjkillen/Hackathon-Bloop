const express = require("express")
const dotenv = require("dotenv")
const path = require("path")


dotenv.config()

pathV = path.resolve(__dirname, "orchestrator/dist")
console.log(pathV)
app = express()
app.use("/", express.static(pathV))
app.listen(process.env.PORT)
console.log(process.env.PORT)
