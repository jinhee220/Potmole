
<!------------------------- TEMPLATE -------------------------------->
<template>
  <div id="capstone-app">
    <!-- Links / Nav Bar / Header -->
    <div id="nav">
      <div class="left">
        <router-link class="link" v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link class="link" v-bind:to="{ name: 'PotholeList' }">Potholes</router-link>
        <router-link class="link" v-bind:to="{ name: 'ReportPothole' }">Report a Pothole</router-link>
        <router-link class="link" v-bind:to="{ name: 'home' }">About</router-link>
        <router-link class="link" v-bind:to="{ name: 'home' }">Contact Us</router-link>
      </div>

      <div class="right">
        <router-link class="link" v-bind:to="{ name: 'login' }" v-if="$store.state.token === ''">Login</router-link>
        <router-link class="link" v-bind:to="{ name: 'register' }">Register</router-link>
        <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>

    <router-view id="views" />

  </div>
</template>

<!------------------------- SCRIPT ---------------------------------->
<script>

export default {

}

</script>


<!------------------------- STYLE ---------------------------------->
<style scoped>
#capstone-app {
  height: 80rem;
}

#views {
  margin: -5% 0% 0% 0%;
  height: 100%;
  width: 100%;
}

#nav {
  display: flex;
  background-color: #E8E2DD;
  opacity: .7;
  border-radius: 10px;
  height: 40px;
  width: 98%;
  margin: auto;
  align-content: center;
}

#nav>div {
  display: inline-block;
  align-self: center;
  /* changed padding below to do a flex align so that borders can be respected */
  justify-content: space-between;
}

.link {
  color: rgb(0, 0, 0);
  text-decoration: none;
  font-size: 1.2rem;
  padding-left: 10px;
  padding-right: 10px;
  /* border: 2px rgb(0, 0, 0) solid; */
}

.left {
  display: flex;
  /* justify-content: flex-start; */
  flex-grow: 1;
  border-radius: inherit;
  flex-wrap: nowrap;
}

.right {
  display: flex;
  /* justify-content: flex-end; */
  flex-grow: 0;
  /* float: right; */
  border-radius: inherit;
  flex-wrap: nowrap;
}
</style>