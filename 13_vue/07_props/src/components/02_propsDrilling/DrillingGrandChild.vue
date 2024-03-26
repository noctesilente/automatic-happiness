<template>
    <h3>GrandChild: {{ injectCount }}</h3>
    <!-- 할아버지가 담아놨던 걸 꺼내서 수정하고 있음 -->
    <button @click="injectCount++">GrandChild가 증가(수정)</button>
</template>

<script setup>
    import { inject } from 'vue';

    // 어디서 provide로 꺼내놓으면 어디서는 inject로 담아두면 됨
    const injectCount = inject('count');
</script>