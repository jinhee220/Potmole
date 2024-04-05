
<!------------------------- TEMPLATE -------------------------------->
<template>
  <div id="capstone-app">

    <!-- Links / Nav Bar / Header -->
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
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
<style>

</style>