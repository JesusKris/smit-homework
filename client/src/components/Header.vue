<template>
    <header>
        <nav class="navbar w-full relative flex flex-row items-center justify-center">
            <h1 class="nexa-bold title">Secret Contacts</h1>
        </nav>
    </header>
</template>


<style>
.title {
    text-overflow: ellipsis;
    white-space: pre;
    width: auto;
    word-break: normal;
    font-size: 3vw;
}

.navbar {
    height: 65px;
}


@media (min-width: 600px) {
    .navbar {
        height: 80px;
    }
}

@media (max-width: 600px) {
    .title {
        font-size: 18px
    }
}

@media (min-width: 1200px) {
    .title {
        font-size: 40px
    }
}
</style>