#include <unistd.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <unistd.h>
#include <sys/stat.h>
#include <stdlib.h> 

void run(int array[3][2]){
    int fd[2];
    pipe(fd);
    printf("%i\n", fork());
    /*if(!fork()){
        close(STDOUT_FILENO);
        dup2(fd[0],STDOUT_FILENO);
        close(fd[0]);
        printf("I am second process\n");
    }else{
        close(STDIN_FILENO);
        dup2(fd[1],STDIN_FILENO);
        close(fd[1]);
        printf("I am first process\n");
    }*/
    if(!getpid()){
        printf("I am second process\n");
        
    } else {
        printf("I am first process\n");
    }
}

int main() {
    int array[3][2] = {
        {1,2},
        {1,3},
        {2,4}};
    run(array);
    return 0;
}