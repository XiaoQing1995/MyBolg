// 當 httpStatus 是 403 的時候代表用戶認證失敗，回到登入畫面
import Swal from "sweetalert2";
export const whenErrorCheckHttpStatus = (error, router) => {
    const httpStatus = error.response?.status;
    console.log("httpStatus")
    console.log(httpStatus)

    if (httpStatus == 400) {
        Swal.fire({
          title: "操作失敗",
          icon: "error",
          confirmButtonText: "確定",
        })
      }

    if (httpStatus === 403) {
      cleanLoginStore();
      router.push({ path: "/login"})
      console.log("httpError")
    }
  };

  export const whenErrorCheckHttpStatusForFrontDeskUse = (error, router) => {
    const httpStatus = error.response?.status;
    if (httpStatus == 400) {
      router.push({ path: "/home/articles"})
    }
  }

  function cleanLoginStore() {
    window.localStorage.clear()
  }
  