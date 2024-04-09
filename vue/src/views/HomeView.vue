<template>
  <div class="home">
    <div class="spacer"></div>
    <div class="logo">
      <img src="src\assets\image.png" />
    </div>
    <h2>Your go-to hub for tracking and fixing pesky potholes!</h2>
    <div class="box">
      <h3>What We Do</h3>
      <p> Our platform simplifies pothole reporting, facilitating quick repairs and smoother rides.
        With real-time updates and seamless communication, PotMole empowers communities to tackle road hazards
        efficiently.
        Say goodbye to bumpy roads and hello to smoother travels with PotMole!</p>
    </div>
  </div>
</template>

<script>

export default {

};
</script>

<style scoped>
* {

  background-color: transparent;

}

.spacer {
  height: 1.5rem;
}

.home {

  background-image: url("src/assets/background.webp");
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;

}

.logo {

  display: flex;
  justify-content: center;
  padding-top: 130px;
  z-index: 2;
  background-color: none;

}

div>img {

  width: 60%;
  height: auto;
}

div>h2 {

  color: rgb(0, 0, 0);
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  text-align: center;
  font-size: 22.3px;
  padding-bottom: 100px;
  font-weight: bolder;

}

.box p {

  color: rgb(0, 0, 0);
  font-weight: bolder;
  text-align: left;

}

.box h3 {

  color: rgb(0, 0, 0);
  font-size: 35px;
  text-align: left;

}

.box {

  background-color: #E8E2DD;
  opacity: .7;
  width: 50%;
  margin: auto;
  border-radius: 10px;
  border-left: 10px solid #E8E2DD;
  border-bottom: .5px solid #E8E2DD;




}
</style>
