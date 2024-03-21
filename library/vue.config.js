const { defineConfig } = require('@vue/cli-service')
// const webpack = require('webpack');
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    proxy:{
      '/api':{
        target:'http://localhost:8081',
        pathRewrite:{'^/api':''},
        ws:true,
        changeOrigin:true
      }
    }
  },
  // configureWebpack: {
  //   plugins: [
  //     new webpack.ProvidePlugin({
  //       $: "jquery",
  //       jQuery: "jquery",
  //       "window.jQuery": "jquery",
  //       Popper: ["popper.js", "default"]
  //     })
  //   ]
  // }
})
