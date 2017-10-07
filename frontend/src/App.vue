<template lang="pug">
  v-app(toolbar--fixed toolbar style='overflow hidden')
    router-view
</template>

<script>

</script>

<style lang="stylus">
:root
  --main-color #F06D06
  --main-color-dark #b25206

html
  height 100%
  font-size 100%
  overflow hidden!important
.application--light
  background-color rgba(0,0,0,0)!important
.tabs__slider
  height 3px!important
.toolbar__content
  background-color black!important
.toolbar
  z-index 30
body
  width 100%
  height 100%
  padding 0
  margin 0
  overflow auto!important
  font-family 'Roboto', sans-serif
a:hover
  text-decoration none!important
p
  margin-bottom 0px!important


.contain
  flex 1
  max-width 1368px
  margin 0 auto
  display flex
  flex-direction column

.design
  flex 1
  display flex
  flex-direction column
  justify-content center
  align-items stretch

.text
  text-align center
  vertical-align center

.shadow
  box-shadow 0 1px 5px rgba(0,0,0,.2), 0 2px 2px rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12)
.button
  padding 1rem
  background-color var(--main-color)
  color #fff
  font-size 1rem
  font-weight bold
  flex 1
  display flex
  justify-content center
  align-items center
  border-radius 3px
  line-height 1rem
  &:hover
    background-color var(--main-color-dark)

.bcm
  background-color var(--main-color)

.row
  flex-direction row
.column
  flex-direction column
.center
  justify-content center
  align-items center
.rowStart
  flex-direction row
  justify-content flex-start
.rowEnd
  flex-direction row
  justify-content flex-end
.row600
  @media (min-width: 600px)
    flex-direction row

eval(i)
  if i == 0
    return initial!important
  return i rem

for i in (0..5)
  .f{i}
    flex i
  .m{i}
    margin eval(i)
  .mt{i}
    margin-top eval(i)
  .mr{i}
    margin-right eval(i)
  .mb{i}
    margin-bottom eval(i)
  .ml{i}
    margin-left eval(i)

  .p{i}
    padding eval(i)
  .pt{i}
    padding-top eval(i)
  .pr{i}
    padding-right eval(i)
  .pb{i}
    padding-bottom eval(i)
  .pl{i}
    padding-left eval(i)

</style>
