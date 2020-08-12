// vue.config.js

var webpack = require('webpack');

const path = require('path');

module.exports = {
  outputDir: path.resolve(__dirname, "../src/main/resources/static"),
  css: {
    requireModuleExtension: false
  },
  configureWebpack: {
    plugins: [
      new webpack.ProvidePlugin({
        $: 'jquery',
        jquery: 'jquery',
        'window.jQuery': 'jquery',
        jQuery: 'jquery'
      })
    ]
  }
}