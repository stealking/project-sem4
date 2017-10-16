<template lang="pug">
  v-app(toolbar--fixed toolbar style='overflow hidden')
    router-view
</template>

<script>

</script>

<style lang="stylus">
:root
  --main-color #8433b2
  --main-color-dark #8800c6

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
[type=number]
  width 100%
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
  display flex
  flex-direction column
  justify-content center
  align-items stretch

.text
  text-align center
  vertical-align center
.bold
  font-weight bold

.shadow
  box-shadow 0 1px 5px rgba(0,0,0,.2), 0 2px 2px rgba(0,0,0,.14), 0 3px 1px -2px rgba(0,0,0,.12)
.button
  background-color var(--main-color)
  color #fff
  font-size 1rem
  font-weight bold
  display flex
  justify-content center
  align-items center
  border-radius 5px
  border: 1px solid #dcdcdc;
  box-shadow: 0 0 14px rgba(0, 0, 0, 0.2);
  &:hover
    background-color var(--main-color-dark)
.buttonHeight
  height 40px
.bcm
  background-color var(--main-color)

.row
  flex-direction row
.column
  flex-direction column
.center
  justify-content center
  align-items center
.sa
  justify-content space-around
.rowStart
  flex-direction row
  justify-content flex-start
.rowEnd
  flex-direction row
  justify-content flex-end
.columnStart
  flex-direction column
  justify-content flex-start
.columnEnd
  flex-direction column
  justify-content flex-end
.row600
  @media (min-width: 600px)
    flex-direction row
.labelBox
  border solid 1px #cbcbcb
  border-radius 5px
.fw
  flex-wrap wrap
.sd
  border: 1px solid #dcdcdc;
  box-shadow: 0 0 14px rgba(0, 0, 0, 0.2);
  border-radius: 4px;
.tsd
  text-shadow 1px 1px 2px #000000;
._card
  width 320px
.el-select
  width 100%
eval(i)
  if i == 0
    return initial!important
  return (0.5 * i) rem
.cw
  color white
.bcp
  background-color purple
evals(i)
  if i == 0
    return initial!important
  return (0.25 * i) rem

for i in (20..0)
  .f{i}
    flex i
  .s{i}
    font-size evals(i)
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

.removeYPaddingYMarginOnVeryNarrow
  @media (max-width: 400px)
    padding-left 0
    padding-right 0
    margin-right 0
    margin-left  0
</style>
