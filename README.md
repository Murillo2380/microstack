# microstack
Versión simplificada del stack tecnógico de la diputación a efectos de formación

# Instalar SDK

## Instalar NodeJS

  - Comprobar versión de node con ''node -v''.
  - En caso de que no sea la versión v12.x.y (LTS). 
    - **Desinstalar** NodeJS.
    - Descargar e instalar NodeJS - https://nodejs.org/
    - Comprobar que npm funciona, si dice que es incompatible desinstalarlo con ''npm uninstall -g npm''

## Actualizar npm y typescript

```
npm install -g npm
npm install -g typescript@latest
```

En windows puede que tengamos que ejecutar en PowerShell lo siguiente para poder usar tsc:

```
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

## Instalar tslint

npm install -g tslint

## Instalar y actualizar GIT

Instalar Git for Windows  [[https://gitforwindows.org/|https://gitforwindows.org/]]

## Instalar Java

Descargar el JDK de Java de [AdoptJDK.Net](https://adoptopenjdk.net/)

  * Para ''Java 8'' elegir ''Otras plataformas'', ''Windows'', aquitectura ''x64'', ''JDK'', el fichero ''zip''.

Descargar el zip y descomprimir el ''C:\opt''.

## Instalar Eclipse

  - Descargar el [instalador de Eclipse](https://www.eclipse.org/downloads/packages/installer)
  - Arrancarlo, comprobar si hay actualizaciones (pone UPDATE el menú del icono del bocadillo). Si no hay un Java en el sistema (recomendado), se puede arrancar el instalador con JAVA_HOME=/c/opt/jdk8… ~/Downloads/eclipse-inst-win64.exe usando git-bash.
  - En advanced mode, pinchar en el icono de +. En el diálogo que se abre pinchar el "Browse File System" y elegir el fichero ''DacorunaSDK... .setup'' en el directorio eclipse del raiz del git de subtel. 
  - Elegir la JVM y "Next" hasta la pantalla de variables. En la pantalla de variables marcar "Show all variables", y poner c:\opt\ en "Root install folder". 
  - Finish para hacer la instalación. 
  - Eclipse estará instalado en c:\opt\sdk-*
  - Arrancar eclipse
  - En ''Window -> Preferences -> General -> Workspace ... Window title'', marcar ''Show perspective name'' y ''Show full path workspace''.
  - En ''Window -> Preferences -> Java -> Code Style -> Formatter''. Importar el formato del fichero ''dacoruna-java-formatter.xml'' que está en el directorio eclipse del raiz del git de subtel.

## Importar proyectos

Una vez instalado eclipse hay que importar los proyectos de SUBTEL.

Para esto, lo normal es cambiarse a la perspectiva de Java y en la ventana Package Explorer, activar Top level elements -> Working Sets.

Importar el proyecto de tipo eclipse que está en git/eclipse en el working set "Other Projects".


## Configuración del espacio de trabajo

### Herramientas git

  - Estando en la perspectiva Java, ir a Windows -> Perspective -> Customize perspective.
  - En la pestaña "Action Set Availability" marcar "Git"
  - Aparecerá una barra de comandos que podemos arrastrar a la esquina inferior derecha de eclipse.

### Filtrado de tipos en autocompletar

En ''Window -> Preferences ... Type Filters'' añadir

  * java.lang.Object
  * java.awt.*
  * java.swing.*
  * antlr.*
  * org.apache.commons.lang.StringUtils


### Instalar Visual Studio Code 

Descargar el [instalador](https://code.visualstudio.com/).

Instalar la versión de usuario, no la de sistema.

Una vez instalado, añadir las siguientes extensiones, desde linea de comando:

```
code --install-extension esbenp.prettier-vscode
code --install-extension miclo.sort-typescript-imports
code --install-extension zengxingxin.sort-js-object-keys
code --install-extension ms-vscode.vscode-typescript-tslint-plugin
```

### Limpieza periódica

Comprobar los módulos npm instalados a nivel global:

```npm -g list --depth 0```

Eliminar los que sobren con ''npm -g rm <paquete>''


# Arrancar el proyecto

La primera vez (o cada vez que se cambie el esquema de la base de datos) hay que ejecutar en Eclipse (Run -> Run Configurations) la tarea "microstack-jooq install". Esta tarea, hace las migraciones necesaria de la base de datos y genera las clases JOOQ de soporte.

Una vez hecho eso, ya se puede arrancar la aplicación con la tarea "Main - Desa". Para desarrollo mejor arrancarla con Debug -> Debug Configurations.

Cuando arranque se puede navegar a http://localhost:8080/

Para arrancar la consola de H2 se pude usar, ajustando la ruta de java:

´´´/c/opt/jdk8u242-b08/bin/java -jar ~/.m2/repository/com/h2database/h2/1.4.200/h2-1.4.200.jar  -url jdbc:h2:file:~/.microstack/db -user sa```



