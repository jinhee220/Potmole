<template>
  <div class="home">
    <div class="spacer"></div>
    <div class="logo">
      <img src="src\assets\image.png" />
    </div>
    <h2>Your go-to hub for tracking and fixing pesky potholes!</h2>
    <div class="btn-center">
      <div class="btnbox">
        <router-link :to="{ name: 'PotholeList' }" tag="button" class="btn"> 🚧View Potholes🚧 </router-link>
      </div>
      <div class="btnbox">
        <router-link :to="{ name: 'ReportPothole' }" tag="button" class="btn"> 🚧Report Potholes🚧 </router-link>
      </div>
    </div>
    <div class="box">
      <h3>What We Do</h3>
      <p> Our platform simplifies pothole reporting, facilitating quick repairs and smoother rides.
        With real-time updates and seamless communication, PotMole empowers communities to tackle road hazards
        efficiently.
        Say goodbye to bumpy roads and hello to smoother travels with PotMole!</p>
    </div>
    <div class="spacer-bot"></div>
    <footer>
      Copyright © PotMole Enterprises
    </footer>
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
  background-color: rgb(255, 255, 255);
  opacity: .25;
  height: 4rem;
}

.spacer-bot {
  height: 5rem;
}

.home {
  background-image: url("src/assets/background.webp");
  background-repeat: no-repeat;
  background-size: cover;
}

.logo {

  display: flex;
  justify-content: center;
  padding-top: 180px;
  z-index: 2;
  background-color: none;

}

div>img {
  width: 60%;
  height: auto;
}

div>h2 {
  color: rgb(0, 0, 0);
  text-align: center;
  font-size: 22.3px;
  padding-bottom: 120px;
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
  padding-top: 10px;
}

.box {
  background-color: #E8E2DD;
  opacity: .8;
  width: 50%;
  margin: auto;
  border-radius: 10px;
  border-left: 10px solid #E8E2DD;
  border-bottom: .5px solid #E8E2DD;
}

.btn {
  border-radius: 10px;
  border: 2px solid black;
  color: black;
  background-color: #E8E2DD;
  padding: 10px;
  font-size: 2rem;
  font-weight: bolder;
}

.btnbox {
  display: inline-block;
  width: 40%;
  text-align: center;
  padding: 20px 20px 70px 20px;
  opacity: .8;
}

.btn-center {
  display: flex;
  justify-content: center;
}

footer {
  display: flex;
  background-color: #E8E2DD;
  opacity: .7;
  border-radius: 10px;
  height: 20px;
  width: 250px;
  align-content: left;
  margin-top: 5%;
  margin-left: 1%;
  padding-left: 18px;
}

@media screen and (max-width: 400px) {
  .btnbox {
    width: 40%;
  }

  .btn {
    font-size: .7rem;
  }

  .box p {
    font-size: 15px;
  }

  .box {
    width: 70%;
  }

  .logo {
    padding-top: 50px;
  }

  .btnbox {
    padding: 10px;
  }

  .spacer {
    height: 50px;
  }

  div>h2 {
    padding-bottom: 50px;
  }

}
</style>
