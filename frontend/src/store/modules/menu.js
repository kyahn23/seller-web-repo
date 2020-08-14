export const namespaced = true

export const state = {
  initMenu: -1
}

export const mutations = {
  SET_INIT_MENU(state, initMenu) {
    state.initMenu = initMenu
  }
}

export const actions = {
  setInitMenu({
    commit
  }, initMenu) {
    commit('SET_INIT_MENU', initMenu)
  }
}