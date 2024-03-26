<template>
    <!-- 부모로부터 물려받은 걸 객체 형태로 묶어서 받은 다음에 하나하나씩 풀고 있음 -->
    <h3>전달받은 props: {{ props.message }}</h3>
</template>

<script setup>
    import { defineProps } from 'vue';

    /* defineProps 함수를 활용해 String형의 message props를 받아 props 변수에 담는다. */
    const props = defineProps({
        message: String
    })
</script>

<style scoped>

</style>