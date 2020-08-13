import Vue from 'vue'
import VueRouter from 'vue-router'
import ServiceInbox from '@/views/service-inbox.vue'
import ServiceVisit from '@/views/service-visit.vue'
import ServiceMarketing from '@/views/service-marketing.vue'
import ServiceStatus from '@/views/service-status.vue'
import CarrDealMng from '@/views/carr-deal-manage.vue'
import CarrMntrtMng from '@/views/carr-mntrt-manage.vue'
import ShopEmployees from '@/views/shop-employees.vue'
import ShopManage from '@/views/shop-manage.vue'
import ShopNotice from '@/views/shop-notice.vue'

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
  },
  {
    path: '/service/marketing',
    name: 'service-marketing',
    component: ServiceMarketing
  },
  {
    path: '/service/status',
    name: 'service-status',
    component: ServiceStatus
  },
  {
    path: '/carr/deal/:carrier',
    name: 'carr-deal',
    component: CarrDealMng
  },
  {
    path: '/carr/mntrt/:carrier',
    name: 'carr-mntrt',
    component: CarrMntrtMng
  },
  {
    path: '/shop/employees',
    name: 'shop-employees',
    component: ShopEmployees
  },
  {
    path: '/shop/manage',
    name: 'shop-manage',
    component: ShopManage
  },
  {
    path: '/shop/notice',
    name: 'shop-notice',
    component: ShopNotice
  }
]

const router = new VueRouter({
  mode,
  routes
})

export default router