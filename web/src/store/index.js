import { createStore } from 'vuex'
import ModuleUser from './user';
import ModuleStudent from './student';
export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    student: ModuleStudent,
  }
})
