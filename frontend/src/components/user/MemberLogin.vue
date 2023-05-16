<template>
  <v-main class="pa-16 ma-16">
      <!-- Login Component -->
    <v-container style="max-width: 550px" fill-height>
      <v-layout align-center row wrap>
        <v-flex xs12>
          <v-card>
            <div class="pa-10">
              <h1 style="text-align: center" class="mb-10">Login</h1>
              <form>
                <v-text-field
                  label="ID"
                  prepend-inner-icon="mdi-account"
                  v-model="userId"
                  required
                ></v-text-field>
                <v-text-field
                  prepend-inner-icon="mdi-lock"
                  type="password"
                  label="Password"
                  v-model="userPwd"
                  required
                >
                </v-text-field>
                <v-btn
                  type="submit"
                  color="blue lighten-1 text-capitalize"
                  depressed
                  large
                  block
                  dark
                  class="mb-3"
                  @click="loginSubmit"
                >
                  Login
                </v-btn>
                <v-btn
                  @click="addUserShow"
                  color="blue lighten-1 text-capitalize"
                  depressed
                  large
                  block
                  dark
                >
                  Sign Up
                </v-btn>
              </form>
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
export const loginurl = "localhost:80/member/login";
export default {
  data() {
    return {
      userId : '',
      userPwd : '',
    };
  },
  components: {},
  computed: {},
  methods: {
    loginSubmit(){
        let saveData = {};
        saveData.userId = this.userId;
        saveData.userPwd = this.userPwd;
        try{
            this.$axios
                .post(loginurl, JSON.stringify(saveData), {
                    headers: {
                        "Content-Type": `application/json`,
                    },
                })
                .then((res) => {
                if(res.status === 200) {
                    // 로그인 성공시 처리
                    this.$router.push('/');
                }
            });
        } catch (error) {
            console.error(error);
        }
    },
      addUserShow(){

      }

  },
};
</script>