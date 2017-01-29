"use strict";

const path = require("path"),
      webpack = require("webpack"),
      HtmlWebpackPlugin = require("html-webpack-plugin"),
      ExtractTextPlugin = require("extract-text-webpack-plugin");

const nodeExternals = require('webpack-node-externals');


module.exports = {
  entry: [path.join(path.join(__dirname, "src"), "main.js")],
  externals: [nodeExternals({
    whitelist: ["jquery"]
  })],
  target: 'node',
  output: {
    path: path.join(__dirname, "dist"),
    filename: "bundle.js"
  },

  resolve: {
    extensions: ["", ".js", ".css", ".scss"],
  },

  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loader: "babel?{presets: ['es2015']}",
      },
      {
        test: /\.html$/,
        loader: "html"
      },
      {
        test: /\.json$/,
        loader: "json"
      },
      {
        test: /\.hbs$/,
        loader: "handlebars"
      },
      {
        test: /\.scss$/,
        loader: ExtractTextPlugin.extract("style-loader", `css-loader!sass-loader`)
      },
    ]
  },

  plugins: [
    new HtmlWebpackPlugin({
      template: "src/index.hbs",
      title: require("./package.json").name
    }),
    new webpack.ProvidePlugin({
        React: "react"
    }),
    new ExtractTextPlugin("[name].css")
  ]
};