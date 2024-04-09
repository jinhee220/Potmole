
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
#nav>div {
  display: inline-block;
  padding-bottom: 5px;
  border: 1px solid black;
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
  justify-content: flex-start;
  flex-grow: 1;
}

.right {
  display: flex;
  justify-content: flex-end;
  flex-grow: 1;
  float: right;
}

</style>