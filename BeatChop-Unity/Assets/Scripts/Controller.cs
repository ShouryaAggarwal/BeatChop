using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Controller : MonoBehaviour {
    public float speed = 0.5f;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown(KeyCode.LeftArrow)) {
            rotate(0);
        } else if (Input.GetKeyDown(KeyCode.RightArrow)) {
            rotate(1);
        } else if (Input.GetKeyDown(KeyCode.UpArrow)) {
            rotate(2);
        } else if (Input.GetKeyDown(KeyCode.DownArrow)) {
            rotate(3);
        } else if (Input.GetKeyDown(KeyCode.A)) {
            if (transform.position.x > -2) {
                float step = speed * Time.deltaTime;
                Vector3 target = new Vector3(-2, transform.position.y, transform.position.z);
                transform.position = Vector3.MoveTowards(transform.position, target, step);
            }
        } else if (Input.GetKeyDown(KeyCode.D)) {
            if (transform.position.x < 2)
            {
                float step = speed * Time.deltaTime;
                Vector3 target = new Vector3(2, transform.position.y, transform.position.z);
                transform.position = Vector3.MoveTowards(transform.position, target, step);
            }
        }
    }

    void rotate(int i) {
        float rotateAmount = 0;
        if (i == 0) {
            while (rotateAmount > -65) {
                transform.Rotate(0.0f, -2.0f * Time.deltaTime, 0.0f);
                rotateAmount += -2.0f * Time.deltaTime;
            }
            while (rotateAmount < 65)
            {
                transform.Rotate(0.0f, 2.0f * Time.deltaTime, 0.0f);
                rotateAmount += 2.0f * Time.deltaTime;
            }
        }
        else if (i == 1)
        {
            while (rotateAmount < 65)
            {
                transform.Rotate(0.0f, 2.0f * Time.deltaTime, 0.0f);
                rotateAmount += 2.0f * Time.deltaTime;
            }
            while (rotateAmount > -65)
            {
                transform.Rotate(0.0f, -2.0f * Time.deltaTime, 0.0f);
                rotateAmount += -2.0f * Time.deltaTime;
            }
        }
        else if (i == 3)
        {
            while (rotateAmount < 25)
            {
                transform.Rotate(2.0f * Time.deltaTime, 0.0f, 0.0f);
                rotateAmount += 2.0f * Time.deltaTime;
            }
            while (rotateAmount > -25)
            {
                transform.Rotate(-2.0f * Time.deltaTime, 0.0f, 0.0f);
                rotateAmount += -2.0f * Time.deltaTime;
            }
        }
        else if (i == 2)
        {
            while (rotateAmount > -25)
            {
                transform.Rotate(-2.0f * Time.deltaTime, 0.0f, 0.0f);
                rotateAmount += -2.0f * Time.deltaTime;
            }
            while (rotateAmount < 25)
            {
                transform.Rotate(2.0f * Time.deltaTime, 0.0f, 0.0f);
                rotateAmount += 2.0f * Time.deltaTime;
            }
        }
    }
}
