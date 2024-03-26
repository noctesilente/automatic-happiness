
<!--
    자식 컴포넌트가 부모 컴포넌트로 발산(emit(해당 이벤트 이름))
-->

<template>
    <!-- 부모의 toggle이라는 이벤트를 실행하라는 뜻 -->
    <button @click="emit('toggle')">다크 모드</button>
</template>

<script setup>
    import {defineEmits} from 'vue';

    /* 부모로부터 물려받은 toggle이라는 이름의 이벤트를 호출할 수 있는 함수 반환받기(defineEmits를 활용하며 배열로 이벤트 이름 작성) */
    // 객체가 아닌 배열 모습으로 만들기
    const emit = defineEmits(['toggle']);
</script>
