using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class Onclick : MonoBehaviour {
    public GameObject blockprefab;
    public Transform blockspawn;
    public float spawnTime = 3.0f;
    void Start()
    {
        InvokeRepeating("blocks", spawnTime, spawnTime);
    }

    public void blocks() {
        //GameObject block = GameObject.Instantiate(blockprefab);
        Vector3 pos = new Vector3(Random.Range(-2f,2f), 0.6f, -2f);
        GameObject block = (GameObject)Instantiate(blockprefab, pos, blockspawn.rotation);
        block.GetComponent<Rigidbody>().velocity = block.transform.forward * -6.0f;
        Destroy(block, 2);
    }
}
