const express = require("express")
const dotenv = require("dotenv")

dotenv.config()


app = express()
app.listen(process.env.PORT)
console.log(process.env.PORT)
