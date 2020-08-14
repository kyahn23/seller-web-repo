import Vue from 'vue'
import Vuex from 'vuex'
import * as menu from '@/store/modules/menu.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    menu
  },
  state: {}
})