<template>
  <div>
    <navtop></navtop>
    <router-view></router-view>
  </div>  
</template>

<script>
// import router from '../../../router';
import auth from '../../../auth';
import navtop from './navtop-login';
import login from './login';
import register from './register';

export default {
  components: { navtop, login, register },
  data() {
    return {
      credentials: {
        username: '',
        password: '',
      },
      error: '',
      ruleForm: {
        username: '',
        pass: '',
      },
      rules: {
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' },
        ],
        pass: [
          { required: true, trigger: 'blur' },
          { min: 5, message: 'Length should be min 5', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const credentials = {
            username: this.ruleForm.username,
            password: this.ruleForm.pass,
          };
          auth.login(credentials, 'Admin', '').then(() => {
            this.error = 'Wrong username or password!';
          });
        }
      });
    },
  },
};
</script>
<style scoped>
@import '../../../assets/css/style.css';

.el-button {
  color: white;
  background: #1ab394;
  border: none;
  padding: .7em 0;
  outline: none;
  width: 44%;
  line-height: 1.6;
  font-size: 1em;
  cursor: pointer;
  letter-spacing: 1px;
  font-weight: 400;
  font-family: 'Open Sans', sans-serif;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -o-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -ms-transition: 0.5s all;
  border-radius: 30px;
  text-align: center;
}

.el-button:hover {
  background: #fff;
  color: black;
  transition: 0.5s all;
  -webkit-transition: 0.5s all;
  -o-transition: 0.5s all;
  -moz-transition: 0.5s all;
  -ms-transition: 0.5s all;
}

.row-bg {
  padding-top: 20vh;
}

.error-class {
  text-align: center;
  color: red;
}


.bg-info {
  background-color: transparent !important;
}


</style>
