<template>
    <v-app-bar app color="grey-light" flat>

        <!-- 좌 상단 홈 버튼 -->
        <v-col cols="auto">
            <v-btn elevation="8" size="x-large" :to="{ name: 'home' }">홈(버튼 좀 구진듯)</v-btn>
        </v-col>


        <!-- 중앙 메뉴 탭 -->
        <v-tabs centered class="ml-n9" color="black darken-1">
            <v-tab :to="{ name: 'signin' }">로그인</v-tab>
            <v-tab :to="{ name: 'boardList' }">공지사항</v-tab>
            <v-tab :to="{ name: 'tour' }">여행정보</v-tab>
            <v-tab :to="{ name: 'mypage' }">여행계획</v-tab>
        </v-tabs>


        <!-- 우측 회원 관리 창 -->
        <v-avatar
            class="hidden-sm-and-down"
            color="grey darken-1 shrink"
            size="32"
            style="margin-right: 10px;"
        ><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt=""></v-avatar>

        <div class="text-center">
            <v-menu
            v-model="menu"
            :close-on-content-click="false"
            :nudge-width="200"
            offset-x
            >
            <template v-slot:activator="{ on, attrs }">
                <v-btn
                color="blue lighten-1 text-capitalize"
                dark
                v-bind="attrs"
                v-on="on"
                >
                ID들어감
                </v-btn>
            </template>

            <v-card>
                <v-list>
                <v-list-item>
                    <v-list-item-avatar>
                    <img
                        src="https://cdn.vuetifyjs.com/images/john.jpg"
                        alt=""
                    >
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title>Name</v-list-item-title>
                    
                        <v-list-item-subtitle>ID or Email</v-list-item-subtitle>
                    </v-list-item-content>

                </v-list-item>
                </v-list>

                <v-divider></v-divider>

                <v-list>
                    <v-list-item>
                        <v-list-item-action>
                        <v-switch
                            v-model="message"
                            color="purple"
                        ></v-switch>
                        </v-list-item-action>
                        <v-list-item-title>Enable messages</v-list-item-title>
                    </v-list-item>

                    <v-list-item>
                        <v-list-item-action>
                        <v-switch
                            v-model="hints"
                            color="purple"
                        ></v-switch>
                        </v-list-item-action>
                        <v-list-item-title>Enable hints</v-list-item-title>
                    </v-list-item>
                </v-list>

                <v-card-actions>
                    <v-spacer></v-spacer>

                    <v-btn
                        text
                        @click="menu = false"
                    >
                        Cancel
                    </v-btn>
                    <v-btn
                        color="primary"
                        text
                        @click="menu = false"
                    >
                        Save
                    </v-btn>
                </v-card-actions>
            </v-card>
            </v-menu>
        </div>
    </v-app-bar>
</template> 

<script>
export default {
    data: () => ({
        links: ["공지사항", "로그인", "회원가입", "마이페이지", "로그아웃", "FAQS"],
    }),
};
</script>

<style>
router-link {
    text-decoration: none;
}
</style>