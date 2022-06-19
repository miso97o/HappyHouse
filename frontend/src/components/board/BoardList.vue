<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
      <b-col cols="5" class="sm-3">
        <b-form-input v-model="word" placeholder="공지사항 검색"></b-form-input>
      </b-col>
      <b-col cols="1" class="sm-3">
        <b-button variant="primary" @click="searchBoard"
          ><b-icon icon="search"></b-icon
        ></b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in articles"
              :key="article.qnano"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <b-row align-h="center">
      <b-pagination
        class="text-center"
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </b-row>
  </b-container>
</template>

<script>
/* eslint-disable */
import { listArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";
import { apiInstance } from "../../api";
const api = apiInstance();
export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      key: "",
      word: "",
      perPage: 10,
      currentPage: 1,
      rows: 0,
    };
  },
  created() {
    listArticle(
      { pageNo: 1 },
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
    api.get("/qna/cnt").then((res) => {
      this.rows = res.data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    searchBoard() {
      listArticle(
        { key: "question", word: this.word },
        (res) => {
          this.articles = res.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
