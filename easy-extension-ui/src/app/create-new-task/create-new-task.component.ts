import { Component, OnInit } from '@angular/core';
import {App, LibFile} from '../object/App';
import {AppService} from './app.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SharedService} from '../shared/shared.service';

declare var $: any;
declare var WebUploader: any;
declare var layer: any;
var uploaderJS;
@Component({
  selector: 'app-create-new-task',
  templateUrl: './create-new-task.component.html',
  styleUrls: ['./create-new-task.component.css'],
  providers: [ AppService ]
})

export class CreateNewTaskComponent implements OnInit {

  app: App = new App();
  files: Array<FileUpload> = new Array<FileUpload>();
  ferror: ErrorMessage=new ErrorMessage();

  hiddenAlert:boolean=true;
  alertContent = '';
  constructor(private router: Router, private  routerInfo: ActivatedRoute, private  appService: AppService, private sharedService: SharedService) {

  }

  ngOnInit() {
    this.sharedService.navEvent.emit(1);
    this.webuploderInit();

    let id=this.routerInfo.snapshot.params['appId'];
    if(id!=null && id>0){
      this.appService.getAppById(id).subscribe(app=>{
        this.app=app;
      });
    }
  }

  showAppNameValue( ){
    this.ferror=new ErrorMessage();
    let reg = /^[a-zA-z]\w{2,15}$/;
    if(!reg.test(this.app.appName==null ? '' : this.app.appName)){
      this.ferror.error=true;
      this.ferror.appName='字母、数字、下划线组成，字母开头，3-16位';
    }

    if(this.app.appDesc!=null && this.app.appDesc.length>300){
      this.ferror.error=true;
      this.ferror.appDesc='不能超过300字符';
    }
    let availabeActionCls=false;
    if(this.app.libs!=null && this.app.libs.length>0){
      this.app.libs.forEach(lib=>{
        if(lib.executeClasses!=null && lib.executeClasses.length>0){
          availabeActionCls=true;
        }
      });
    }

    if(availabeActionCls==false){
      this.app.actionClass=null;
    }

    if(this.app.actionClass==null){
      this.ferror.error=true;
      this.ferror.actionClass='没有找到程序执行入口';
    }

    if(this.app.libs==null){
      this.ferror.error=true;
      this.ferror.libs='请上传jar文件';
    }

    console.log(this.ferror.error==false);
    if(this.ferror.error==false){
      this.alertContent='';
      this.appService.saveApp2(this.app).subscribe(app => {
        let errs = app['error'];

        if (errs != null) {
          errs.forEach(em => {
            this.alertContent += em + ', ';
          });
          this.hiddenAlert = false;
        }else{
          this.app=app;
          this.router.navigate(['/apps']);
        }

        let val=JSON.stringify(app);
        console.log(val);
      });
    }
  }

  dismissAlert(hidden: boolean){
    this.hiddenAlert=hidden;
  }

  removeLibFile(lib: LibFile){
    let index=this.app.libs.indexOf(lib);
    this.app.libs.splice(index,1);
    let fls=uploaderJS.getFiles();
    if(fls!=null){
      fls.forEach(fl=>{
        console.log(fl);
        if(lib!=null && lib.fileName==fl['name']){
          uploaderJS.removeFile(fl['id']);
        }
      });
    }
  }

  webuploderInit() {
    const $list = $('#thelist'),
      $btn = $('#ctlBtn');

    const uploader = WebUploader.create({
      resize: false, // 不压缩image
      swf: 'webuploader/Uploader.swf', // swf文件路径
      server: 'uploadfile', // 文件接收服务端。
      pick: '#picker23', // 选择文件的按钮。可选
      chunked: false, //是否要分片处理大文件上传
      chunkSize: 2 * 1024 * 1024, //分片上传，每片2M，默认是5M
      auto: true //选择文件后是否自动上传
      // chunkRetry : 2, //如果某个分片由于网络问题出错，允许自动重传次数
      //runtimeOrder: 'html5,flash',
      // accept: {
      //   title: 'Images',
      //   extensions: 'gif,jpg,jpeg,bmp,png',
      //   mimeTypes: 'image/*'
      // }
    });
    uploaderJS=uploader;
    // 当有文件被添加进队列的时候
    /*
    uploader.on( 'fileQueued', function( file ) {
      $list.append( '<div id="' + file.id + '" class="item">' +
        '<span class="info">' + file.name + '</span>&emsp;' +
        '<span class="state">等待上传...</span>&emsp;' +
        '</div>' );
    });
    */
    uploader.on( 'fileQueued',( file )=> {
      let fu: FileUpload =new FileUpload();
      fu.fileName=file.name;
      fu.fileId=file.id;
      fu.state='等待上传...';
      fu.percent=0;
      this.files.push(fu);
    });
    // 文件上传过程中创建进度条实时显示。
    /*
    uploader.on( 'uploadProgress', function( file, percentage ) {
      let $li = $( '#' + file.id ),
        $percent = $li.find('.progress .progress-bar');

      // 避免重复创建
      if ( !$percent.length ) {
        $percent = $('<div id="fileProgressBar" class="progress progress-striped active" style="height: 10px;"> ' +
          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
          '</div>' +
          '</div>').appendTo( $li ).find('.progress-bar');
      }

      $li.find('span.state').text('上传中');

      $percent.css( 'width', percentage * 100 + '%' );
    });
    */
    uploader.on( 'uploadProgress',( file, percentage )=> {
      let fileUp:FileUpload=this.files.filter(fu=>{
        if(fu.fileId==file.id){
          return fu;
        }
      })[0];
      if(fileUp.state !='上传中...') {
        fileUp.state = "上传中...";
      }
      fileUp.percent=percentage;
    });
    // 文件上传成功
    /*
    uploader.on( 'uploadSuccess', ( file, res )=> {
      $( '#' + file.id ).find('span.state').text('已上传');
      $( '#' + file.id ).append('<a href="#">删除</a>');
      this.app.libs.push(res);
    });
    */
    uploader.on( 'uploadSuccess', ( file, res )=> {
      let fileUp:FileUpload=this.files.filter(fu=>{
        if(fu.fileId==file.id){
          return fu;
        }
      })[0];
      fileUp.state='已上传';
      fileUp.libfile=res;
      this.app.libs.push(res);

      if(this.app.libs!=null){
        this.app.libs.forEach(lib=>{
          if(lib.executeClasses!=null && lib.executeClasses.length>0){
            if(this.app.actionClass!=lib.executeClasses[0].name && lib.executeClasses[0].name!=null){
              this.app.actionClass=lib.executeClasses[0].name;
              return;
            }
          }
        });
      }
      console.log('array leng:'+this.app.libs.length);

      let index=this.files.indexOf(fileUp);
      this.files.splice(index,1);
    });

    uploader.on( 'uploadError', ( file )=> {
      let fileUp:FileUpload=this.files.filter(fu=>{
        if(fu.fileId==file.id){
          return fu;
        }
      })[0];
      fileUp.state='上传失败';
    });
    /*
    // 文件上传失败，显示上传出错
    uploader.on( 'uploadError', function( file ) {
      $( '#' + file.id ).find('span.state').text('上传出错');
    });
    // 完成上传完
    uploader.on( 'uploadComplete', function( file ) {
      $( '#' + file.id ).find('.progress').fadeOut();
    });
    */
    /*
    $btn.on('click', function () {
      if ($(this).hasClass('disabled')) {
        return false;
      }
      uploader.upload();
      // if (state === 'ready') {
      //     uploader.upload();
      // } else if (state === 'paused') {
      //     uploader.upload();
      // } else if (state === 'uploading') {
      //     uploader.stop();
      // }
    });
    */
  }

}

export class FileUpload{
  public fileId: string;
  public fileName: string;
  public state: string;
  public percent: number;
  public libfile: LibFile;

  constructor( ){

  }
}

export class ErrorMessage{
  public appName: string;
  public appDesc: string;
  public actionClass: string;
  public libs: string;
  public error: boolean = false;
  constructor( ){

  }
}

