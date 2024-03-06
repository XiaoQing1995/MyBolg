<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <h4 class="card-title">Login</h4>
          </div>
          <div class="card-body">
            <div class="form-group">
              <label for="username">Username</label>
              <input
                v-model="userDetails.accountNumber"
                type="text"
                class="form-control"
                id="username"
                placeholder="Enter your username"
              />
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input
                v-model="userDetails.accountPassword"
                type="password"
                class="form-control"
                id="password"
                placeholder="Enter your password"
              />
            </div>
            <p></p>
            <button class="btn btn-primary" @click="submit">Login</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { login } from "../api/api";
import Swal from "sweetalert2";

const router = useRouter();

const userDetails = reactive({
  accountNumber: "",
  accountPassword: "",
});

const urlPath = "/v1/auth/authenticate";

const submit = async () => {
  try {
    const response = await login(urlPath, userDetails);
    const httpStatus = response.status;
    if (httpStatus == 200) {
      router.push("backstage/articles");
    }
  } catch (error) {
    const httpStatus = error.response?.status;
    if (httpStatus == 403) {
      Swal.fire({
        title: "登入失敗",
        icon: "error",
        confirmButtonText: "確定",
      });
    }
  }
};
</script>
