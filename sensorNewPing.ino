#include <NewPing.h>

#define TRIGGER_PIN  11
#define TRIGGER_PIN2  3
#define ECHO_PIN     10
#define ECHO_PIN2     2
#define MAX_DISTANCE 20



int distanciaSonar, distanciaSonar2 = 0;
boolean ativo , ativo2 = false;
long pessoasDentro = 0;

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);

NewPing sonar2(TRIGGER_PIN2, ECHO_PIN2, MAX_DISTANCE);

void setup() {
  Serial.begin(9600);
}

void loop() {

  distanciaSonar = sonar.ping_cm();

  if (distanciaSonar != 0) {
    delay(200);
    distanciaSonar2 = sonar2.ping_cm();
    if (distanciaSonar2 != 0) {
      Serial.println("entrou");
      ativo = true;
    } else {
      ativo = false;
    }
  } else {
    ativo = false;
  }

  if (ativo) {
    pessoasDentro++;
    Serial.println(pessoasDentro);
    ativo = false;
  }

}
