<template>
    <v-app-bar app color="grey-light" flat>

        <!-- 좌 상단 홈 버튼 -->
        <v-col cols="auto">
            <v-btn elevation="2" size="x-large" :to="{ name: 'home' }" class="white"><v-img src="@/assets/img/logo.png" :width="100" :to="{ name: 'home' }"></v-img></v-btn>
        </v-col>


        <!-- 중앙 메뉴 탭 -->
        <v-tabs centered class="ml-n9" color="black darken-1">
            <v-tab :to="{ name: 'signin' }">로그인</v-tab>
            <v-tab :to="{ name: 'article' }">Q&A</v-tab>
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
                UserId
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

                <v-list rounded>
                    <v-subheader>REPORTS</v-subheader>
                    <v-list-item-group
                        v-model="selectedItem"
                        color="primary"
                    >
                        <v-list-item
                        v-for="(item, i) in items"
                        :key="i"
                        >
                        <v-list-item-icon>
                            <v-icon v-text="item.icon"></v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title v-text="item.text"></v-list-item-title>
                        </v-list-item-content>
                        </v-list-item>
                    </v-list-item-group>
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
        selectedItem: 1,
        items: [
            { text: 'Real-Time', icon: 'mdi-clock' },
            { text: 'Audience', icon: 'mdi-account' },
            { text: 'Conversions', icon: 'mdi-flag' },
        ],
    }),
};
</script>

<style>
router-link {
    text-decoration: none;
}
</style>