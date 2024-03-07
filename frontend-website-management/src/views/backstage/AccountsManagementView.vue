<template>
  <div>
    <button class="btn btn-success" @click="insertAccount">新增帳戶</button>
    <AccountItem
      v-for="account in accounts"
      :key="account.accountId"
      :account="account"
      :roles="roles"
      @deleteAccount="refreshViewGetAccounts"
      @updateAccount="refreshViewGetAccounts"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { apiGet, apiPost } from "@/api/api";
import { useRouter } from "vue-router";
import AccountItem from "@/components/backstage/AccountItem.vue";
import Swal from "sweetalert2";

const urlPathAccounts = "/v1/accounts";
const urlPathRoles = "/v1/roles";
const urlPathAuthentication = "/v1/auth"

// const account = ref([]);
const accounts = ref([]);
const roles = ref();

const router = useRouter();

const getAccounts = async () => {
  const response = await apiGet(urlPathAccounts, router);
  console.log(response.data);
  accounts.value = response.data.content;
};

const getRoles = async () => {
  const response = await apiGet(urlPathRoles, router);
  roles.value = response.data;
};

const refreshViewGetAccounts = () => {
  getAccounts();
};

const insertAccount = async () => {
  const { value: formValues } = await Swal.fire({
    title: "新增帳戶",
    html:
      '<input id="accountNumber" class="swal2-input" placeholder="帳號">' +
      '<input id="accountPassword" type="password" class="swal2-input" placeholder="密碼">' +
      '<select id="role" class="swal2-select">' +
      roles.value.map((role) => `<option value="${role}">${role}</option>`).join('') +
      '</select>',
    inputAttributes: {
      autocapitalize: "off",
    },
    showCancelButton: true,
    confirmButtonText: "新增",
    cancelButtonText: "取消",
    preConfirm: () => {
      return {
        accountNumber: document.getElementById("accountNumber").value,
        accountPassword: document.getElementById("accountPassword").value,
        role: document.getElementById("role").value.toUpperCase(),
      };
    },
  });

  if (formValues) {
    const { accountNumber, accountPassword, role } = formValues;

    const newAccount = {
      accountNumber: accountNumber,
      accountPassword: accountPassword,
      role: role,
    };

    try {
      const response = await apiPost(`${urlPathAuthentication}/register`, newAccount, router);
      const httpStatus = response.status;

      if (httpStatus == 200) {
        Swal.fire({
          title: "新增成功",
          icon: "success",
          confirmButtonText: "確定",
        }).then(() => {
          getAccounts();
        });
      }
    } catch (error) {
      console.error("Error inserting account:", error);
    }
  }
};

onMounted(() => {
  getAccounts();
});
getRoles();
</script>
