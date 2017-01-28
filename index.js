const express = require("express")
const dotenv = require("dotenv")

dotenv.config()


app = express()
app.use(express.static("orchesrtator/dist"))
app.listen(process.env.PORT)
console.log(process.env.PORT)
