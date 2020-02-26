
# istiodeploy-tutorial

Este tutorial tiene por objetivo mostrar la configuración mínima que se requiere para desplegar un microservicio Spring Boot en [Istio Service mesh](https://istio.io/).

El tutorial está basado en la instalación de [minishift versión 1.34.2](https://github.com/minishift/minishift/releases/tag/v1.34.2) sobre Ubuntu 18.04 y usando la [versión 1.4.5 de Istio](https://github.com/istio/istio/releases/tag/1.4.5).

El tutorial supone que en el equipo host está instalado [Oracle Virtual Box](https://www.virtualbox.org/). La versión que se utilizó para este tutorial es la 5.2.34_Ubuntu r133883.

# Tabla de contenido 

1. [istiodeploy-tutorial](#istiodeploy-tutorial)
2. [Tabla de contenido](#tabla-de-contenido)
3. [Instalación de docker-ce](#instalación-de-docker-ce)
4. [Instalación de minishift](#instalación-de-minishift)
	1. [Configurar primera ejecución de minishift](#configurar-primera-ejecución-de-minishift)
	2. [Levantar el cluster de minishift](#levantar-el-cluster-de-minishift)
		1. [Validar la instalación de la consola de minishift](#validar-la-instalación-de-la-consola-de-minishift)
		2. [Configurar la variable PATH](#configurar-la-variable-path)
		3. [Conectarse al cluster de minishift](#conectarse-al-cluster-de-minishift)
5. [Instalación de Istio](#instalación-de-istio)
	1. [Descargar Istio Service Mesh](#descargar-istio-service-mesh)
	2. [Configurar la variable PATH](#configurar-la-variable-path-1)
	3. [Desplegar los pods de istio en minishift](#desplegar-los-pods-de-istio-en-minishift)
	4. [Levantar servicios básicos](#levantar-servicios-básicos)


# Instalación de docker-ce

Para instalar docker-ce puede seguir los siguientes pasos:

```bash
$ sudo apt-get install apt-transport-https ca-certificates  curl gnupg-agent software-properties-common

$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

$ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs)  stable"

$ sudo apt update
$ sudo apt-get install docker-ce docker-ce-cli containerd.io -y

# Verificar la instalacion
$ sudo usermod -aG docker $USER
```

En este punto es recomendable salir de la sesión de ssh o la terminal activa y volver a ingresar a la misma.

# Instalación de minishift

Descargar minishift de https://github.com/minishift/minishift/releases/download/v1.34.2/minishift-1.34.2-linux-amd64.tgz


```bash
$ mkdir installMinishift
$ cd installMinishift
$ wget https://github.com/minishift/minishift/releases/download/v1.34.2/minishift-1.34.2-linux-amd64.tgz
$ tar zxvf minishift-1.34.2-linux-amd64.tgz

$ cd minishift-1.34.2-linux-amd64
$ pwd

# Agregar la salida del comando pwd a la variable MINISHIFT_HOME en el archivo .bashrc
export MINISHIFT_HOME=...
export PATH=$PATH:MINISHIFT_HOME

# Una vez que se hayan agregado las variables anteriores al archivo .bashrc ejecutar:
$ cd
$ . ./.bashrc

# Validar la versión de minishift
$ minishift version
minishift v1.34.2+83ebaab
```


## Configurar primera ejecución de minishift

Ejecutar los siguientes comandos para configurar la primera ejecución de minishift

**Nota**: Estos comandos sólo se ejecuta una vez, para posteriores ejecuciones es suficiente con ejecutar los comandos del paso posterior a éste.

```bash
$ minishift profile set istio-demo
$ minishift config set memory 8GB
$ minishift config set cpus 4
$ minishift config set vm-driver virtualbox
$ minishift config set image-caching true
$ minishift addon enable admin-user
$ minishift config set openshift-version v3.11.0
```
## Levantar el cluster de minishift

```bash
# La primera vez que se ejecuta el comando start se descargan tanto la imagen 
# de centos para crear la VM como los binarios de OpenShift.
$ minishift start
...
OpenShift server started.

The server is accessible via web console at:
    https://IP:8443/console

You are logged in as:
    User:     developer
    Password: <any value>

To login as administrator:
    oc login -u system:admin

# Una vez levantado el cluster de minishift ejecutar:
$ minishift addon apply admin-user
$ minishift ssh -- sudo setenforce 0
$ minishift addon apply anyuid
```
### Validar la instalación de la consola de minishift

Para validar la correcta instalación de la consola abrir un browser e ingresar la URL devuelta en el paso anterior https://IP:8443/console donde IP es la dirección IP del cluster de minishift.

### Configurar la variable PATH

Si se decide agregar el PATH del comando oc al archivo .bashrc este paso sólo se realiza una vez, en caso contrario cada vez que se inicie sesión deberá ejecutarse los comandos correspondientes.

```bash
# Ejecutar y agregar la salida al archivo .bashrc
# recordar que una vez que se haya modificado el archivo .bashrc 
# hay que ejecutar:
# $ cd
# $ . ./.bashrc
$ minishift oc-env

# En su caso ejecutar el siguiente comando, sin embargo se tiene que 
# ejecutar cada vez que se inicie sesión en una terminal.
$ eval $(minishift oc-env)
```

### Conectarse al cluster de minishift

```bash
$ oc login -u system:admin -n default
```


# Instalación de Istio

## Descargar Istio Service Mesh

```bash
$ cd  ~/installMinishift
$ wget https://github.com/istio/istio/releases/download/1.4.5/istio-1.4.5-linux.tar.gz
$ tar zxvf istio-1.4.5-linux.tar.gz

$ cd istio-1.4.5/
```

## Configurar la variable PATH
Se requiere agregar a la variable PATH la ruta donde se encuentra el archivo binario 

```bash
# Recuperar la salida del siguiente comando:
$ pwd

# Agregar la variable ISTIO_HOME en el archivo .bashrc 
# y modificar la variable PATH
export ISTIO_HOME=[salida_del_comando_pwd]
export PATH=$ISTIO_HOME/bin:$PATH

# Una vez modificado el archivo .bashrc ejecutar:

$ cd
$ . ./.bashrc
```

## Desplegar los pods de istio en minishift

```bash
$ cd ~/installMinishift/istio-1.4.5/

$ oc login -u system:admin -n default
$ for i in install/kubernetes/helm/istio-init/files/crd*yaml; do oc apply -f $i; done
$ oc apply -f install/kubernetes/istio-demo.yaml

$ oc adm policy add-scc-to-user anyuid -z default -n istio-system
$ oc adm policy add-scc-to-group anyuid system:serviceaccounts -n istio-system
```

## Levantar servicios básicos

```bash
# Cambiarse al proyecto istio-system

$ oc project istio-system

$ oc expose svc istio-ingressgateway --port=80
$ oc expose svc grafana
$ oc expose svc prometheus
$ oc expose svc tracing
$ oc expose service kiali --path=/kiali
$ oc adm policy add-cluster-role-to-user admin system:serviceaccount:istio-system:kiali-service-account -z default

# Verificar el estado de los pods

$ oc get pods

# El proceso puede tardar unos minutos, al final la salida de comando anterior debería verse como:

NAME                                      READY     STATUS      RESTARTS   AGE
grafana-6f6bdfc4d9-k5b9r                  1/1       Running     0          4m
istio-citadel-5ddf4c9cd6-9q4d6            1/1       Running     0          4m
istio-egressgateway-8658b5cf55-9mbg2      1/1       Running     0          4m
istio-galley-6576f455c8-hzrp8             1/1       Running     0          4m
istio-grafana-post-install-1.4.5-swpkc    0/1       Completed   0          4m
istio-ingressgateway-7cd84b884d-dtqmt     1/1       Running     0          4m
istio-pilot-c56b46dc6-9r67d               2/2       Running     3          4m
istio-policy-5978bd75dc-lcg5c             2/2       Running     1          4m
istio-security-post-install-1.4.5-9mtrx   0/1       Completed   0          4m
istio-sidecar-injector-6c6f49cb85-8nm68   1/1       Running     0          4m
istio-telemetry-64477d66d5-z9rc4          2/2       Running     1          4m
istio-tracing-7dd9b9548f-bbq8p            1/1       Running     0          4m
kiali-ffff7c54d-8xlmh                     1/1       Running     0          4m
prometheus-79876855d8-c6678               1/1       Running     0          4m
```