import React, { Component } from 'react';
import {
  Alert,
    View,
    Platform
} from 'react-native';
import { CameraKitCameraScreen } from '../../src';
import CheckingScreen from './CheckingScreen';


export default class CameraScreen extends Component {

  constructor(props) {
    super(props);
    this.state = {
      example: undefined
    };
  }

  onBottomButtonPressed(event) {
    const captureImages = JSON.stringify(event.captureImages);
    Alert.alert(
      `${event.type} button pressed`,
      `${captureImages}`,
      [
        { text: 'OK', onPress: () => console.log('OK Pressed') },
      ],
      { cancelable: false }
    )
  }

  render() {
    if (this.state.example) {
      const CameraScreen = this.state.example;
      return <CameraScreen />;
    }
    return (
        <View style={{flex:1}}>
            <CameraKitCameraScreen
            showFrame
            scanBarcode
            laserColor={'red'}
            frameColor={'white'}
            onReadCode={(event) => console.log('LOG CODE', event)}
            hideControls
            offsetForScannerFrame={Platform.OS === 'android' ? 120 : 30}
            heightForScannerFrame={Platform.OS === 'android' ? 120 : 150}
            colorForScannerFrame={'blue'}
            />
          <View style={{flex:1}} >

          </View>
        </View>
    );
  }
}



