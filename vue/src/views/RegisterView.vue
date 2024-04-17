<template>
  <div class="page">
    <div class="spacer"></div>
    <div id="register" class="text-center">
      <form class="form" v-on:submit.prevent="register">
        <h1>Create Account</h1>
        <hr class="divide">
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group" id="first-input">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password </label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="form-input-group" id="last-input">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
        <button type="submit"><strong>Create Account</strong></button>
        <p><router-link class="link" v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
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

/* .spacer {
  height: 66px;
  background-color: #bbbab9;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: -1;
} */

.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

#register {
  margin: auto;
  text-align: center;
  background-color: #BBBAB9;
  border: 2px solid #BBBAB9;
  border-radius: 10px;
  width: 45%;
  box-shadow: gray 5px 5px 5px;
  margin-top: 50px;
}

input[type="text"],
input[type="password"] {
  width: 50%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

#confirmPassword {
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

/* #last-input {
  text-align: left;
  margin-left: 12.5%;
} */

* {
  color: black;
}

.form-input-group {
  display: grid;
}

.form-input-group>* {
  margin: auto;
}

@media screen and (max-width: 400px) {
  #register {
    width: 80%;

  }

  label {
    font-size: 1rem;
  }

  #confirmPassword {
    width: 50%;
  }

  .form-input-group {
    display: grid;
  }

  .form-input-group>* {
    margin: auto;
  }
}

@media screen and (max-width: 1400px) {
  .form-input-group {
    display: grid;
  }

  .form-input-group>* {
    margin: auto;
  }

  label {
    font-size: 1.2rem;
  }
}
</style>
