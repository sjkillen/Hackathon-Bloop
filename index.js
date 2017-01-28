const dotenv = require("dotenv")
const path = require("path")
const express = require("express")

dotenv.config()

app = express()
app.listen(process.env.PORT)

app.use("/sound", express.static(path.resolve(__dirname, "sounds")))
app.use("/", express.static(path.resolve(__dirname, "orchestrator/dist")))
