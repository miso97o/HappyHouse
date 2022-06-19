<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-3">
      <b-col>
        <b-form-select v-model="key" :options="keys"></b-form-select>
      </b-col>
      <b-col cols="8" class="sm-3">
        <b-form-input v-model="word" placeholder="뉴스 검색"></b-form-input>
      </b-col>
      <b-col cols="1" class="sm-3">
        <b-button variant="danger" @click="searchAll" v-if="this.searched"
          ><b-icon icon="arrow-repeat"></b-icon
        ></b-button>
        <b-button variant="primary" @click="searchNews" v-else
          ><b-icon icon="search"></b-icon
        ></b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="newsList.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th class="align-middle">No</b-th>
              <b-th class="text-center align-middle">뉴스</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <news-list-item
              v-for="article in newsList"
              :key="article.articleno"
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
import { apiInstance } from "@/api/index";
import NewsListItem from "./NewsListItem.vue";
const api = apiInstance();
export default {
  data() {
    return {
      newsList: [],
      keys: [
        { value: "", text: "선택하세요" },
        { value: "title", text: "제목" },
        { value: "content", text: "내용" },
      ],
      key: "",
      word: "",
      perPage: 10,
      currentPage: 1,
      rows: 0,
      searched: false,
    };
  },
  components: {
    NewsListItem,
  },
  created() {
    this.searchAll();
    this.getCnt();
  },
  methods: {
    searchNews() {
      api
        .get(`/news/list?pageNo=1&key=${this.key}&word=${this.word}`)
        .then((res) => {
          console.log(res);
          this.newsList = res.data;
          this.rows = this.newsList.length;
        });
      this.searched = true;
    },
    searchAll() {
      api.get("/news/list", {}).then((res) => {
        console.log(res);
        this.newsList = res.data;
      });
      this.searched = false;
      this.getCnt();
    },
    getCnt() {
      api.get("/news/cnt").then((res) => {
        this.rows = res.data;
      });
    },
  },
  watch: {
    currentPage: function (val) {
      console.log(val);
      api.get(`/news/list?pageNo=${this.currentPage}`).then((res) => {
        console.log(res);
        this.newsList = res.data;
      });
    },
  },
};
</script>

<style></style>
