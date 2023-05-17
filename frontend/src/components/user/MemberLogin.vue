<template>
  <v-main class="pa-16 ma-16">
      <!-- Login Component -->
    <v-container style="max-width: 550px" fill-height>
      <v-layout align-center row wrap>
        <v-flex xs12>
          <v-card>
            <div class="pa-10">
              <h1 style="text-align: center" class="mb-10">Login</h1>

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
                  type="button"
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
            </div>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>

import http from "@/axios/http";
export const loginurl = "/member/login";
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
        saveData.memberId = this.userId;
        saveData.memberPw = this.userPwd;
        alert(saveData.memberId);
        alert(saveData.memberPw);
        try{
            http.post(loginurl, saveData).then(response => {
                alert(response);
                alert("멈춰");
                if(response.status === 200) {
                    alert(response.status);
                    // 로그인 성공시 처리
                    this.$router.push({name:'home'});
                }else{
                    alert("로그인 실패함 다시하셈 아무튼 다시해 다시다시다시1");
                    // this.$router.push({name:'signin'});
                }
            });
        }catch(error){
            alert("로그인 실패함 다시하셈 아무튼 다시해 다시다시다시2");
            this.$router.push({name:'user/signin'});

        }

    },
      addUserShow(){
      }

  },
};
</script>