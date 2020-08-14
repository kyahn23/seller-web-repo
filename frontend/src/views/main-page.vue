<template>
  <div>
    <h1>MAIN</h1>
    <span @click="setInitMenu(0)">
      <router-link :to="{ name: 'service-inbox' }">Go to Service Inbox</router-link>
    </span>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  methods: mapActions("menu", ["setInitMenu"]),
};
</script>