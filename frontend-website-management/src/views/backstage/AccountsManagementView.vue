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
    <Page
      v-if="showPage"
      :currentPage="currentPage"
      :totalPages="totalPages"
      :changePage="changePage"
    />
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from "vue";
import { apiGet, apiPost } from "@/api/api";
import { useRouter } from "vue-router";
import AccountItem from "@/components/backstage/AccountItem.vue";
import Swal from "sweetalert2";
import Page from "@/components/unit/Page.vue";

const urlPathAccounts = "/v1/accounts";
const urlPathRoles = "/v1/roles";

// const account = ref([]);
const accounts = ref([]);
const roles = ref();

const currentPage = ref(1);
const totalPages = ref(0);
const pageSize = 10;
const showPage = ref(false);

const router = useRouter();

const getAccounts = async () => {
  const response = await apiGet(`${urlPathAccounts}?page=${currentPage.value - 1}&size=${pageSize}`, router);
  console.log(response.data);
  accounts.value = response.data.content;
  totalPages.value = response.data.totalPages;
  showPageWhenDOMRender();
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
      roles.value.map((role) => `<option value="${role}">${role}</option>`).join("") +
      "</select>",
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
    if (!validateAccount(accountNumber, accountPassword, role)) {
      return;
    }
    const account = {
      accountNumber: accountNumber,
      accountPassword: accountPassword,
      role: role,
    };

    try {
      const response = await apiPost(
        `${urlPathAccounts}`,
        account,
        router
      );
      const httpStatus = response.status;
      console.log(response);

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
  getRoles();
  getAccounts();
});

const changePage = async (page) => {
  if (page >= 1 && page <= totalPages.value) {
    currentPage.value = page;
    await getAccounts();
  }
};

const showPageWhenDOMRender = () => {
  nextTick(() => {
    showPage.value = true;
  });
};

const validateAccount = (accountNumber, accountPassword, role) => {
  if (!accountNumber.trim() || !accountPassword.trim() || !role.trim()) {
    Swal.fire("錯誤", "請填寫所有欄位", "error");
    return false;
  }
  return true;
};
</script>
