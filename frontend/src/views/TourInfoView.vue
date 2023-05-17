<template>
<v-container 
    class="bv-example-row mt-3 text-center"
    style="max-width: 1000px;"
    >
<h3 class="underline-hotpink">
    <b-icon icon="journals"></b-icon> Tour info
</h3>
<router-view></router-view>
</v-container>
</template>

<script>
export default {
    name: 'TourInfoView',
};
</script>
