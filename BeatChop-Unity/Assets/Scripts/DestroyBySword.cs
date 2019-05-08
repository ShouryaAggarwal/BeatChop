using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DestroyBySword : MonoBehaviour {

    void OnTriggerEnter(Collider other) {
        Destroy(gameObject);
    }
}
