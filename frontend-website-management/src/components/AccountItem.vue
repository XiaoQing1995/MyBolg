<template>
  <div class="article-item card p-0">
    <!-- 添加 p-0 移除 card 的 padding -->
    <div class="row">
      <div class="col-md-8">
        <div class="card-body">
          <p class="card-text">{{ account.accountNumber }}</p>
          <p class="card-text">{{ account.role }}</p>
        </div>
      </div>
      <div class="col-md-3 d-flex justify-content-end align-items-center mt-2">
        <button class="btn btn-primary mx-2" @click="updateAccountPassword">
          修改密碼
        </button>
        <button class="btn btn-primary mx-2" @click="updateAccountRole">
          修改身份
        </button>
        <button class="btn btn-danger mx-2" @click="deleteAccount">刪除</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits, ref } from "vue";
import { useRouter } from "vue-router";
import { apiDelete, apiUpdate } from "@/api/api";
import Swal from "sweetalert2";
const router = useRouter();

const props = defineProps(["account", "roles"]);
const emit = defineEmits(["deleteAccount", "updateAccount"]);

const urlPathAccount = "/v1/accounts";

const roleOptions = {};
props.roles.forEach((role) => {
  roleOptions[role.toLowerCase()] = role;
});

const updateAccountPassword = async () => {
  console.log(props.roles[0]);
  const { value: password } = await Swal.fire({
    title: `請設定 ${props.account.accountNumber} 的密碼`,
    input: "password",
    inputPlaceholder: "Enter your password",
    inputAttributes: {
      maxlength: "10",
      autocapitalize: "off",
      autocorrect: "off",
    },
  });
  if (password) {
    const updateAccount = ref({
      accountId: props.account.accountId,
      accountNumber: props.account.accountNumber,
      accountPassword: password,
      role: props.account.role,
    });
    const response = await apiUpdate(urlPathAccount, updateAccount.value, router);
    const httpStatus = response.status;
    if (httpStatus == 200) {
      Swal.fire({
        title: "修改成功",
        icon: "success",
        confirmButtonText: "確定",
      }).then(() => {
        emit("updateAccount");
      });
    }
  }
};



const updateAccountRole = async () => {
  let { value: role } = await Swal.fire({
    title: `請選擇 ${props.account.accountNumber} 的身份`,
    input: "select",
    inputOptions: roleOptions,
    inputPlaceholder: props.account.role,
    showCancelButton: true,
    inputValidator: (value) => {
      return new Promise((resolve) => {
        const updateAccount = {
          accountId: props.account.accountId,
          accountNumber: props.account.accountNumber,
          accountPassword: props.account.accountPassword,
          role: value.toUpperCase(),
        };
        if (updateAccount.role !== "") {
          apiUpdate(urlPathAccount, updateAccount, router)
            .then((response) => {
              console.log(response);
              const httpStatus = response.status;
              if (httpStatus === 200) {
                Swal.fire({
                  title: "修改成功",
                  icon: "success",
                  confirmButtonText: "確定",
                }).then(() => {
                  emit("updateAccount");
                });
              }
              resolve();
            })
            .catch((error) => {
              console.error("Error updating account:", error);
              resolve();
            });
        } else {
          resolve();
        }
        // 將非同步操作移到 Promise 內部
      });
    },
  });
};

const deleteAccount = async () => {
  Swal.fire({
    title: "刪除?",
    text: "確定要刪除這個帳戶嗎?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "確定",
    cancelButtonText: "取消", // 設定取消按鈕的文字
  }).then(async (result) => {
    if (result.isConfirmed) {
      const response = await apiDelete(
        `${urlPathAccount}/${props.account.accountId}`,
        router
      );
      const httpStatus = response.status;
      console.log(httpStatus)
      if (httpStatus == 204) {
        Swal.fire({
          title: "刪除!",
          text: "刪除成功",
          icon: "success",
        }).then(() => {
          emit("deleteAccount");
        });
      }
    }
  });
  // 處理刪除文章的邏輯
};
</script>

<style scoped>
.article-item {
  margin-bottom: 20px;
}

.img-fluid {
  max-width: 100%;
  height: auto;
}
</style>
