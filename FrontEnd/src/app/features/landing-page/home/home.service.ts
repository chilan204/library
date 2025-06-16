import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from '../../../../../environments/environment';
import {
  AuthorReqDTO,
  AuthorResDTO,
  BookReqDTO,
  BookResDTO,
  BorrowingReqDTO, BorrowingResDTO,
  CategoryReqDTO,
  CategoryResDTO, UserReqDTO, UserResDTO
} from './interface';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  http = inject(HttpClient);
  apiUrl = environment.API_URL+'/categories';
  apiUrl2 = environment.API_URL+'/books';
  apiUrl3 = environment.API_URL+'/authors';
  apiUrl4 = environment.API_URL+'/borrowings';
  apiUrl5 = environment.API_URL+'/users';

  /* Book */
  // Api-Post
  createData(data: BookReqDTO) {
    return this.http.post<BookResDTO>(this.apiUrl2, data);
  }
  // Api Get All data
  getAllData() {
    return this.http.get<BookResDTO[]>(this.apiUrl2);
  }
  // Api-Get ( Read )
  getDataById(id: number) {
    return this.http.get<BookResDTO>(`${this.apiUrl2}/${id}`);
  }
  // Api-Get ( Read )
  getDataByCategoryId(id: number) {
    return this.http.get<BookResDTO[]>(`${this.apiUrl2}/category/${id}`);
  }

  getDataByAuthorId(id: number) {
    return this.http.get<BookResDTO[]>(`${this.apiUrl2}/author/${id}`);
  }

  //update
  updateData(id: number, data: BookReqDTO) {
    return this.http.put<BookResDTO>(`${this.apiUrl2}/${id}`, data);
  }
  //delete
  deleteData(id: number) {
    return this.http.delete<BookResDTO>(`${this.apiUrl2}/${id}`);
  }

  /* Thể loại */
  // Api-Post
  createData2(data: CategoryReqDTO) {
    return this.http.post<CategoryResDTO>(this.apiUrl, data);
  }
  // Api Get All data
  getAllData2() {
    return this.http.get<CategoryResDTO[]>(this.apiUrl);
  }
  // Api-Get ( Read )
  getDataById2(id: number) {
    return this.http.get<CategoryResDTO>(`${this.apiUrl}/${id}`);
  }
  //update
  updateData2(id: number, data: CategoryReqDTO) {
    return this.http.put<CategoryResDTO>(`${this.apiUrl}/${id}`, data);
  }
  //delete
  deleteData2(id: number) {
    return this.http.delete<CategoryResDTO>(`${this.apiUrl}/${id}`);
  }

  /* Tác giả */
  // Api-Post
  createData3(data: AuthorReqDTO) {
    return this.http.post<AuthorResDTO>(this.apiUrl3, data);
  }
  // Api Get All data
  getAllData3() {
    return this.http.get<AuthorResDTO[]>(this.apiUrl3);
  }
  // Api-Get ( Read )
  getDataById3(id: number) {
    return this.http.get<AuthorResDTO>(`${this.apiUrl3}/${id}`);
  }
  //update
  updateData3(id: number, data: AuthorReqDTO) {
    return this.http.put<AuthorResDTO>(`${this.apiUrl3}/${id}`, data);
  }
  //delete
  deleteData3(id: number) {
    return this.http.delete<AuthorResDTO>(`${this.apiUrl3}/${id}`);
  }

  /* Phiếu mượn */
  // Api-Post
  createData4(data: BorrowingReqDTO) {
    return this.http.post<BorrowingResDTO>(this.apiUrl4, data);
  }
  // Api Get All data
  getAllData4() {
    return this.http.get<BorrowingResDTO[]>(this.apiUrl4);
  }
  getUserData4(username:string) {
    return this.http.get<BorrowingResDTO[]>(`${this.apiUrl4}/user/${username}`);
  }
  // Api-Get ( Read )
  getDataById4(id: number) {
    return this.http.get<BorrowingResDTO>(`${this.apiUrl4}/${id}`);
  }
  //update
  updateData4(id: number, data: BorrowingReqDTO) {
    return this.http.put<BorrowingResDTO>(`${this.apiUrl4}/${id}`, data);
  }
  //delete
  deleteData4(id: number) {
    return this.http.delete<BorrowingResDTO>(`${this.apiUrl4}/${id}`);
  }

  /* Người dùng */
  // Api-Post
  createData5(data: UserReqDTO) {
    return this.http.post<UserResDTO>(this.apiUrl5, data);
  }
  // Api Get All data
  getAllData5() {
    return this.http.get<UserResDTO[]>(this.apiUrl5);
  }
  // Api-Get ( Read )
  getDataById5(id: number) {
    return this.http.get<UserResDTO>(`${this.apiUrl5}/${id}`);
  }
  //update
  updateData5(id: number, data: UserReqDTO) {
    return this.http.put<UserResDTO>(`${this.apiUrl5}/${id}`, data);
  }
  //delete
  deleteData5(id: number) {
    return this.http.delete<UserResDTO>(`${this.apiUrl5}/${id}`);
  }
}
