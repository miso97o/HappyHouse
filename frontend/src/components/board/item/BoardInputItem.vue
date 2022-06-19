<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            disabled
            v-model="article.userid"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="question-group"
          label="제목:"
          label-for="question"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="question"
            v-model="article.question"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="answer-group" label="내용:" label-for="answer">
          <b-form-textarea
            id="answer"
            v-model="article.answer"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
/* eslint-disable */
import { writeArticle, getArticle, modifyArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        question: "",
        answer: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getArticle(
        this.$route.params.articleno,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.question = data.article.question;
          // this.article.answer = data.article.answer;
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    } else {
      this.article.userid = this.userInfo.id;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.question &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.question.focus());
      err &&
        !this.article.answer &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.answer.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.question = "";
      this.article.answer = "";
      this.$router.push({ name: "boardList" });
    },
    registArticle() {
      writeArticle(
        {
          userid: this.article.userid,
          question: this.article.question,
          answer: this.article.answer,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      modifyArticle(
        {
          qnano: this.article.qnano,
          userid: this.article.userid,
          question: this.article.question,
          answer: this.article.answer,
        },
        ({ data }) => {
          let msg = "수정 처리중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
};
</script>

<style></style>
