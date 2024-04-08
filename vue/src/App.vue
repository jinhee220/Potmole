
<!------------------------- TEMPLATE -------------------------------->
<template>
  <div id="capstone-app">

    <!-- Links / Nav Bar / Header -->
    <div id="nav">
      <div class="left">
        <router-link class="link" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link class="link" v-bind:to="{ name: 'PotholeList' }">Pothole List</router-link>
        <router-link class="link" v-bind:to="{ name: 'ReportPothole' }">Report Pothole</router-link>
      </div>

      <div class="right">
        <router-link class="link" v-bind:to="{ name: 'login' }" v-if="$store.state.token === ''">Login</router-link>
        <router-link class="link" v-bind:to="{ name: 'register' }">Register</router-link>
        <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>

    <router-view />

  </div>
</template>

<!------------------------- SCRIPT ---------------------------------->
<script>

export default {

}

</script>


<!------------------------- STYLE ---------------------------------->
<style scoped>
.nav>div {
  display: inline-block;
}

.link {
  color: rgb(78, 85, 85);
  text-decoration: none;
  font-size: 1.5rem;
  padding: 10px;
  border: 2px red solid;
}

.left {
  display: flex;
  justify-content: flex-start;
  flex-grow: 1;
}

.right {
  display: flex;
  justify-content: flex-end;
  flex-grow: 1;
}
</style>