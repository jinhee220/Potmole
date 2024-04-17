<template>
  <div class="page">
    <div class="spacer"></div>
    <div id="login">
      <form class="form" v-on:submit.prevent="login">
        <h1>Please Sign In</h1>
        <hr class="divide">
        <div role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group" id="first-input">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password </label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <button type="submit"><strong>Sign in</strong></button>
        <p>
          <router-link class="link" v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.page {
  background: linear-gradient(to top, #959595, white);
  height: 100%;
}

.spacer {
  height: 4rem;
  /* background-color: #bbbab9; */
}

.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

#login {
  margin: auto;
  text-align: center;
  background-color: #BBBAB9;
  /* border: 2px solid #BBBAB9; */
  border-radius: 10px;
  width: 33%;
  box-shadow: gray 5px 5px 5px;
  margin-top: 50px;
  padding: 2px;

}

input[type="text"],
input[type="password"] {
  width: 50%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button[type="submit"] {
  background-color: #ffbd8b;
  color: black;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  box-shadow: gray 5px 5px 5px;
}

button[type="submit"]:hover {
  background-color: #d68548;
}

.form>h1 {
  font-size: 1.5rem;
}

.form>h1,
label,
.link {
  color: black;
}

.divide {
  border: 1px solid black;
  border-radius: 100px;
  background-color: black;
  width: 90%;
}

#first-input {
  padding-top: 20px;
}

* {
  color: black;
}

@media screen and (max-width: 400px) {
  #login {
    width: 80%;
  }

  label {
    font-size: 1rem;
  }
}
</style>