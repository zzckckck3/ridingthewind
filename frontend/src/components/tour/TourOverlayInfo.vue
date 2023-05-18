<template>
<div ref="overlay">
    <div class="wrap">
    <div class="info">
        <div class="title">
        카카오 스페이스닷원
        <div class="close" @click="closeOverlay" title="닫기"></div>
        </div>
        <div class="body">
        <div class="img">
            <img :src="imageSrc" width="73" height="70">
        </div>
        <div class="desc">
            <div class="ellipsis">{{ address }}</div>
            <div class="jibun ellipsis">{{ jibunAddress }}</div>
            <div><a :href="website" target="_blank" class="link">홈페이지</a></div>
        </div>
        </div>
    </div>
    </div>
</div>
</template>


<script>
export default {
data() {
    return {
    map: null,
    marker: null,
    overlay: null,
    imageSrc: "https://cfile181.uf.daum.net/image/250649365602043421936D",
    address: "제주특별자치도 제주시 첨단로 242",
    jibunAddress: "(우) 63309 (지번) 영평동 2181",
    website: "https://www.kakaocorp.com/main"
    };
},
methods: {
    closeOverlay() {
        this.overlay.setMap(null);
    }
}
};
</script>


<style>
#map {
width: 100%;
height: 300px;
}
</style>