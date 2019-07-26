import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ExamService } from 'src/app/service/exam/exam.service';
import { UserService } from 'src/app/service/login/user.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { subject } from 'src/app/model/subject/subject';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { UserserviceService } from 'src/app/service/user-service/userservice.service';
import * as _ from 'lodash';
import { tap } from 'rxjs/operators';
import { map } from 'rxjs-compat/operator/map';
import { SubjectService } from 'src/app/service/subject/subject.service';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ChapterService } from 'src/app/service/chapter/chapter.service';
import { DomainService } from 'src/app/service/domain/domain.service';
import { UploadfileServiceService } from 'src/app/service/questionservice/uploadfile-service.service';
import { NotifierService } from 'angular-notifier';
export interface Result {
  exam: Object;
  result: Object[];
}
function validatorEmptyInput(
  control: AbstractControl
): { [key: string]: boolean } | null {
  const isWhitespace = (control.value || '').trim().length === 0;
  const isValid = !isWhitespace;
  return isValid ? null : { whitespace: true };
}
function minLengthArray(min: number) {
  return (c: AbstractControl): { [key: string]: any } => {
    if (c.value.length >= min) {
      return null;
    }
    return { minLengthArray: { valid: false } };
  };
}
@Component({
  selector: 'app-practice',
  templateUrl: './practice.component.html',
  styleUrls: ['./practice.component.scss']
})
export class PracticeComponent implements OnInit {
  showRandom: Boolean = false;
  showtable: Boolean = true;
  listPracticeByUser: Object[] = [];
  listSubject: subject[] = [];
  mapNameSubject: Map<number, string>;
  userID: number;
  tokens: string;
  subjectID: number;
  practiceID: number;
  perPage: number;
  idExam: number;
  examResultID: Number;
  examResultID2: Number;
  ListExamResultByUserIDExamID: Object[] = [];
  listExam: Object[] = [];
  listResult: Result[] = [];
  notificationVisibilityWhenDelete = false;
  tenBaiThi: string;
  chuDeBaiThi: string;
  searchBoolean = true;
  listPracticeDone: Object[] = [];
  examUserId: number;
  examId: number;
  name: string;
  title: string;
  code='';
  //add component
  @ViewChild("namepractice") nameField: ElementRef;
  @ViewChild("subject") subjectField: ElementRef;
  constructor(
    private examService: ExamService,
    private jwt: JwtHelperService,
    private userService: UserService,
    private router: Router,
    private subjectService: SubjectService,
    private titleService: Title,
    private userserviceService: UserserviceService,
    private notifierService: NotifierService
  ) {}

  ngOnInit() {
    this.userserviceService.getPracticeOfUser(JSON.parse(localStorage.getItem('item')).id).subscribe( res => {
      this.listPracticeDone = res;
      console.log(this.listPracticeDone);
    })
    // this.mapNameSubject = new Map();
    // this.subjectService.getListSubject().subscribe(res => {
    //   res.map(x => {
    //     this.mapNameSubject.set(x['id'], x['name']);
    //   });
    // });
    // this.notificationVisibilityWhenDelete = false;
    // this.titleService.setTitle('Testonline System - Practice');
    // Phan trang
    this.perPage = 10;
    // // Get list practice and practice result
    // this.userserviceService
    //   .getlistPracticeOfUser(this.userService.userLogin.email)
    //   .pipe(
    //     tap(exams => {
    //       for (let index = 0; index < exams.length; index++) {
    //         this.examService
    //           .getListPracticeResultByUserIDPracticeID(
    //             this.userService.userLogin.id,
    //             exams[index][0]
    //           )
    //           .subscribe(response => {
    //             const result: Result = {
    //               exam: null,
    //               result: []
    //             };
    //             result.exam = exams[index];
    //             result.result = response;
    //             this.listResult.push(result);
    //             console.warn(this.listResult);
    //             this.listResult.sort(function(obj1, obj2) {
    //               return obj1.exam[0] - obj2.exam[0];
    //             });
    //           });
    //       }
    //     })
    //   )
    //   .subscribe(exams => {
    //     // this.listExam = exams;
    //   });
  }
  clickXemChiTiet(id, hetLuot: boolean) {
    this.practiceID = id;
    
    this.router.navigate([
      '/hometotal/practicedetail',
      { paramId: id, paramQualuot: hetLuot }
    ]);
  }
  // Xem ket qua bai da thi
  clickKetQua(id1) {
    this.examService
      .getListExamResultByUserIDExamID(this.userID, id1)
      .subscribe(res => {
        this.ListExamResultByUserIDExamID = res;
        this.examResultID2 = res['id'];
      });
    this.router.navigate([
      '/hometotal/testresult',
      { param1: id1, param2: this.examResultID2 }
    ]);
  }
  // Click vào thi
  clickVaoThi(id: number, ten: string, chude: string) {
    this.idExam = id;
    this.tenBaiThi = ten;
    this.chuDeBaiThi = chude;
    this.notificationVisibilityWhenDelete = true;
  }
  // Chuyen sang history
  clickLichSu() {
    this.router.navigate([
      '/hometotal/examhistory',
      { param1: false, param2: true }
    ]);
  }
  oncg(event: boolean) {
    if (event) {
      const token = this.jwt.decodeToken(localStorage.getItem('access_token'));
      const examResult = {
        email: token['username'],
        exam_id: this.idExam
      };
      const formData = new FormData();
      formData.append('examResult', JSON.stringify(examResult));
      this.examService.getStartExam(formData).subscribe(
        res => {
          this.examResultID = res;
          this.router.navigate([
            '/hometotal/testpractice',
            {
              param1: this.idExam,
              param2: this.examResultID,
              param3: this.tenBaiThi,
              param4: this.chuDeBaiThi
            }
          ]);
        },
        error => {
          
        }
      );
    } else {
      this.notificationVisibilityWhenDelete = false;
    }
  }
  // Phan trang
  trackByFn(index, item) {
    return item.id;
  }
  onChange(event) {
    this.perPage = event.target.value;
  }
  sapXep(param) {
    if (param === 0) {
      this.searchBoolean = !this.searchBoolean;
      if (this.searchBoolean) {
        this.listResult.sort((obj1, obj2) =>
          obj1.exam[1].localeCompare(obj2.exam[1])
        );
      } else {
        this.listResult.sort((obj1, obj2) =>
          obj2.exam[1].localeCompare(obj1.exam[1])
        );
      }
    } else if (param === 1) {
      this.searchBoolean = !this.searchBoolean;
      if (this.searchBoolean) {
        this.listResult.sort((obj1, obj2) =>
          obj1.exam[2].localeCompare(obj2.exam[2])
        );
      } else {
        this.listResult.sort((obj1, obj2) =>
          obj2.exam[2].localeCompare(obj1.exam[2])
        );
      }
    }
  }

  clickRegis( examId: number,examUserId: number,name: string, title: string ){
    this.examId = examId;
    this.name = name;
    this.title = title;
    this.examUserId = examUserId;
  }
  regis(){
    const token = this.jwt.decodeToken(localStorage.getItem('access_token'));
    const examResult = {
      email: token['username'],
      exam_id: this.examId,
      code: '0'
    };
    const formData = new FormData();
    formData.append('examUser', JSON.stringify(examResult));
    this.examService.getStartExam(formData).subscribe(
      res => {
        this.router.navigate([
          '/hometotal/testpractice',
          {
            param1: this.examId,
            param2: this.examUserId,
            param3:this.name,
            param4:this.title
          }
        ]);
      },
      error => {
        this.notifierService.notify('error',error.error.message, '');
      }
    );
  }

}
