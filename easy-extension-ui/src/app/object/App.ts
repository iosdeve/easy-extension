export class App {
  public id: number;
  public appName: string;
  public appDesc: string;
  public actionClass: string;
  public libs: Array<LibFile>;
  public appPath: string;
  public userid: number;
  public created: string;
  public lastUpdated: string;

  constructor( ){
    this.libs = new Array<LibFile>();
  }
}

export class LibFile {
  public id: number;

  public appId: number;

  public fileName: string;

  public name: string;

  public filesize: number;

  public filePath: string;

  public userid: number;

  public created: string;

  public lastUpdated: string;

  public executeClasses: Array<NodeTable> = new Array<NodeTable>();

  constructor( ){

  }
}

export class NodeTable{
  public id: number;
  public parentId: number;
  public referId: number;
  public name: string;
  public description: string;
}
