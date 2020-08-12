import Vue from 'vue'
import VueRouter from 'vue-router'
import ServiceInbox from '@/views/service-inbox.vue'
import ServiceVisit from '@/views/service-visit.vue'

Vue.use(VueRouter)

const mode = 'history'

const routes = [{
    path: '/service/inbox',
    name: 'service-inbox',
    component: ServiceInbox
  },
  {
    path: '/service/visit',
    name: 'service-visit',
    component: ServiceVisit
  }
]

const router = new VueRouter({
  mode,
  routes
})

export default router