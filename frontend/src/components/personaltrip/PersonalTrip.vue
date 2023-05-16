<template>
<v-app id="inspire">

    <v-main class="white">
      <v-container class="text-center">
        <h1>여행계획</h1>
        <v-divider class="my-2"></v-divider>
        <v-row style="margin-top: 10px;">
          <!-- Lefy Map Position -->
          <v-col cols="6">
            <v-sheet
              min-height="70vh"
              max-height="100"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
              <div>맵 들어갈 자리</div>
            </v-sheet>
          </v-col>

          <!-- Center tour pick position -->
          <v-col cols=3>
            <v-sheet
              min-height="70vh"
              max-height="100"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >

            </v-sheet>
          </v-col>

          <!-- Right tour just pick position -->
          <v-col cols=3>
            <v-sheet
              min-height="70vh"
              max-height="100"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
              <v-container fluid>
                <v-row dense>
                  <v-col
                    v-for="card in cards"
                    :key="card.title"
                    :cols="card.flex"
                  >
                    <v-card>
                      <v-img
                        :src="card.src"
                        class="white--text align-end"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="200px"
                      >
                        <v-card-title v-text="card.title" style="font-size: medium;"></v-card-title>
                      </v-img>
                      

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <!-- 버튼 추가 할거면 여기 -->
                        <v-btn icon>
                          <v-icon>mdi-bookmark</v-icon>
                        </v-btn>
                        <v-btn
                          icon
                          @click="card.show = !card.show"
                        >
                          <v-icon>{{ card.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                        </v-btn>
                      </v-card-actions>
                      <v-expand-transition>
                        <div v-show="card.show">
                          <v-divider></v-divider>
                          <v-card-text v-text="card.text"></v-card-text>
                        </div>
                      </v-expand-transition>
                    </v-card>
                  </v-col>
                </v-row>
              </v-container>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    data: () => ({
      cards: [
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', text:'1번 예시입니다', flex: 6, show: false },
        { title: 'Favorite road trips', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', text:'2번 예시입니다', flex: 6, show: false },
        { title: 'Best airlines', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', text: '3번 예시입니다.', flex: 6, show: false },
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', text:'1번 예시입니다', flex: 6, show: false },
        { title: 'Favorite road trips', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', text:'2번 예시입니다', flex: 6, show: false },
        { title: 'Best airlines', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', text:'3번 예시입니다.', flex: 6, show: false },
      ],
    }),
  }
</script>
