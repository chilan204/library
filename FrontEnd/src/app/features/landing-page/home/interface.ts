export interface BookReqDTO {
  name:string;
  description:string;
  authorId: number;
  publicationYear:number;
  image:string;
  categoryId:number;
}
export interface BookResDTO {
  id: number;
  name: string;
  description: string;
  author: AuthorResDTO;
  publicationYear: number;
  image: string;
  category: CategoryResDTO;
}

export interface AuthorReqDTO {
  name: string;
  description: string;
  birthDate: Date;
}

export interface AuthorResDTO {
  id: number;
  name: string;
  description: string;
  birthDate: Date;
}

export interface CategoryReqDTO {
  name: string;
}
export interface CategoryResDTO {
  id: number;
  name: string;
}
export interface BorrowingReqDTO {
  userId: number;
  bookId: number;
  borrowDate: Date;
  dueDate: Date;
  returnDate: Date;
}
export interface BorrowingResDTO {
  id: number;
  user: UserResDTO;
  book: BookResDTO;
  borrowDate: Date;
  dueDate: Date;
  returnDate: Date;
  status: string;
}
export interface UserReqDTO {
  username: string;
  email: string;
  phone: string;
  password: string;
  userCode: string;
}
export interface UserResDTO {
  id: number;
  username: string;
  email: string;
  password: string;
  phone: string;
  userCode: string;
}
